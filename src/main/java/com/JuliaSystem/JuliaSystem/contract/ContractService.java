package com.JuliaSystem.JuliaSystem.contract;

import com.JuliaSystem.JuliaSystem.payment.PaymentInfo;
import com.JuliaSystem.JuliaSystem.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final PaymentService paymentService;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractByID(Integer id) {
        return contractRepository.findById(id).orElseThrow(() -> new IllegalStateException("Contract does not exist"));
    }

    public void newContract(String secondPartyName, String brideName, String groomName,
                            String eventType, String eventLocation, Date eventDate,
                            String civilID, Integer phone1, Integer phone2, Integer contractPrice,
                            String componentIDs, Integer packageID, String comments) {
        Contract contract = new Contract();
        contract.setSecondPartyName(secondPartyName);
        contract.setBrideName(brideName);
        contract.setGroomName(groomName);
        contract.setEventType(eventType);
        contract.setEventLocation(eventLocation);
        contract.setEventDate(eventDate);
        contract.setCivilID(civilID);
        contract.setPhone1(phone1);
        contract.setPhone2(phone2);
        contract.setContractStatus(ContractStatus.InProgress);
        contract.setContractPrice(contractPrice);
        contract.setComponentsIDs(componentIDs);
        contract.setPackageID(packageID);
        contract.setContractStage(ContractStage.Signed);
        contract.setComments(comments);

        contractRepository.save(contract);
    }

    public List<Contract> getContractsByEventDate(Date eventDate) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getEventDate() == eventDate).toList();
    }

    public List<Contract> getContractsByBrideName(String brideName) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getBrideName().contains(brideName)).toList();
    }

    public List<Contract> getContractsByEventType(String eventType) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getEventType() == eventType).toList();
    }

    public List<Contract> getContractsByEventLocation(String eventLocation) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getEventLocation() == eventLocation).toList();
    }

    public List<Contract> getContractsByStatus(ContractStatus contractStatus) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getContractStatus() == contractStatus).toList();
    }

    public List<Contract> getContractsByContractStage(ContractStage contractStage) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(c -> c.getContractStage() == contractStage).toList();
    }

    public void addCommentsToContract(Integer contractID, String comments) {
        Contract contract = getContractByID(contractID);
        String oldComments = contract.getComments();
        if (oldComments.length() != 0)
            contract.setComments(oldComments + "\n" + comments);
        else contract.setComments(comments);
        contractRepository.save(contract);
    }

    public void setPhotographer(Integer contractID, String photographer) {
        Contract contract = getContractByID(contractID);
        if (photographer.length() != 0 && !Objects.equals(contract.getPhotographer(), photographer))
            contract.setPhotographer(photographer);
    }

    public void setVideo(Integer contractID, String video) {
        Contract contract = getContractByID(contractID);
        if (video.length() != 0 && !Objects.equals(contract.getVideo(), video))
            contract.setVideo(video);
    }

    public void setContractStage(Integer contractID, ContractStage contractStage) {
        Contract contract = getContractByID(contractID);
        if (!Objects.equals(contract.getContractStage(), contractStage))
            contract.setContractStage(contractStage);
    }

    public List<Contract> getContractsByPhotographer(String photographer) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(contract -> contract.getPhotographer() == photographer).toList();
    }

    public List<Contract> getContractsByVideo(String video) {
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(contract -> contract.getVideo() == video).toList();
    }

    public List<Object> getContractPaymentsData(Integer contractID) {
        Contract contract = getContractByID(contractID);
        PaymentInfo paymentInfo = new PaymentInfo(paymentService.getPaymentIDsForContract(contractID), (contract.getContractPrice()
                - paymentService.getContractTotalPaid(contractID)), contract.getContractPrice());
        List<Object> paymentData = new ArrayList<Object>();
        paymentData.add(paymentService.getPaymentsForContract(contractID));
        paymentData.add(paymentInfo);
        return paymentData;

    }

  /*  public void makePaymentInContract (Integer contractID,String paymentTitle, Integer amount) {
        Contract contract = getContractByID(contractID);
        paymentService.makePayment(contractID,paymentTitle,amount);
        PaymentInfo totalPaid = getContractPaymentsInfo(contractID);
        if(totalPaid.getDueAmount() == 0 && contract.getContractStage() == ContractStage.PicsCollected) {
            contract.setContractStage(ContractStage.Finished);
            contract.setContractStatus(ContractStatus.Finished);
            contractRepository.save(contract);
        }
    }
*/

    public List<Contract> filterContractsByCriteria(String eventType, Date date, String eventLocation,
                                                    ContractStage contractStage, String photographer,
                                                    String video, ContractStatus status, Integer month,
                                                    String brideName) {
        List<Contract> contracts = getAllContracts();


        return contracts.stream().filter(contract -> contract.getEventType().equals(eventType) ||
                contract.getEventDate().equals(date) || contract.getEventLocation().equals(eventLocation) ||
                contract.getContractStage().equals(contractStage) || contract.getContractStatus().equals(status) ||
                sameMonth(contract.getEventDate(), month) || contract.getBrideName().contains(brideName)).toList();
    }


    public List<Contract> filterContractsByMultipleFilters(String brideName,
                                                           String eventType, String eventLocation,
                                                           ContractStage contractStage) {

        List<Contract> matchingContracts = new ArrayList<Contract>();
        List<Contract> allContracts = getAllContracts();
        Integer filtered = 0;
        if (brideName != null) {
            matchingContracts = allContracts.stream().filter(contract -> contract.getBrideName().equals(brideName)).toList();
            filtered = 1;
        }
        if (eventType != null) {
            if (filtered > 0)
                matchingContracts = matchingContracts.stream().filter(contract -> contract.getEventType().equals(eventType)).toList();
            else {
                matchingContracts = allContracts.stream().filter(contract -> contract.getEventType().equals(eventType)).toList();
                filtered = 1;
            }
        }
        if (eventLocation != null) {
            if (filtered > 0)
                matchingContracts = matchingContracts.stream().filter(contract -> contract.getEventLocation().equals(eventLocation)).toList();
            else {
                matchingContracts = allContracts.stream().filter(contract -> contract.getEventLocation().equals(eventLocation)).toList();
                filtered = 1;
            }
        }
        if (contractStage != null) {
            if (filtered > 0)
                matchingContracts = matchingContracts.stream().filter(contract -> contract.getContractStage().equals(contractStage)).toList();
            else {
                matchingContracts = allContracts.stream().filter(contract -> contract.getContractStage().equals(contractStage)).toList();
                filtered = 1;
            }
        }
        return matchingContracts;
    }


    public boolean sameMonth(Date date, Integer month) {
        if(month != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(Calendar.MONTH) == (month - 1)) return true;
            else return false;
        }
        return false;
    }

    public List<Contract> getContractsInSameMonth (Integer month){
        List<Contract> contracts = getAllContracts();
        return contracts.stream().filter(contract -> sameMonth(contract.getEventDate(),month)).toList();
    }

    public List<Contract> getContractsFromSearch(String search) {
        List<Contract> contracts  = getAllContracts();
        if(!search.isEmpty())
        return  contracts.stream().filter(c -> c.getBrideName().contains(search) || c.getGroomName().contains(search) ||
        c.getCivilID().contains(search) || c.getPhone1().toString().contains(search) ||
                c.getPhone2().toString().contains(search) || c.getSecondPartyName().contains(search)).toList();
        else return new ArrayList<Contract>();
    }
}
