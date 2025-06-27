package com.example.payment.service.impl;


import com.example.payment.dto.PaymentResultDto;
import com.example.payment.model.Participant;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ParticipantRepository participantRepository;

    public List<PaymentResultDto> calculate(Integer eventId){
        List<Participant> participants = participantRepository.findByEventId(eventId);

        BigDecimal totalPaid = calculateTotalPaid(participants);
        BigDecimal equalShare = totalPaid.divide(
                BigDecimal.valueOf(participants.size()), 2, RoundingMode.HALF_UP
        );

        List<Participant> creditors = identifyCreditors(participants, equalShare);
        List<Participant> debtors = identifyDebtors(participants, equalShare);

        return matchDebtorsToCreditors(debtors, creditors);

    }

    private BigDecimal calculateTotalPaid(List<Participant> participants){
        return participants.stream().map(participant -> participant.getAmountPaid() != null ? participant.getAmountPaid(): BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Participant> identifyCreditors(List<Participant> participants, BigDecimal share){
        List<Participant> result = new ArrayList<>();
        for(Participant participant : participants){
            BigDecimal difference = calculatePaymentDifference(participant, share);
            if(difference.compareTo(BigDecimal.ZERO) > 0){
                result.add(new Participant(null, participant.getUser(), difference, null, null, null));
            }
        }
        return result;
    }

    private List<Participant> identifyDebtors(List<Participant> participants, BigDecimal share){
        List<Participant> result = new ArrayList<>();
        for(Participant participant : participants){
            BigDecimal difference = calculatePaymentDifference(participant, share);
            if(difference.compareTo(BigDecimal.ZERO) < 0){
                result.add(new Participant(null, participant.getUser(), difference, null, null, null));
            }
        }
        return result;
    }



    private BigDecimal calculatePaymentDifference(Participant participant, BigDecimal share) {
        BigDecimal paid = participant.getAmountPaid() !=null ? participant.getAmountPaid() : BigDecimal.ZERO;
        return paid.subtract(share);
    }

    private List<PaymentResultDto> matchDebtorsToCreditors(List<Participant> debtors, List<Participant> creditors){
        List<PaymentResultDto> results = new ArrayList<>();
        int i = 0, j = 0;

        while(i < debtors.size() && j < creditors.size()){
            Participant debtor = debtors.get(i);
            Participant creditor = creditors.get(j);


            BigDecimal transfer = debtor.getAmountPaid().min(creditor.getAmountPaid());
            results.add(getPayment(debtor, creditor, transfer));

            debtor.setAmountPaid(debtor.getAmountPaid().subtract(transfer));
            creditor.setAmountPaid(creditor.getAmountPaid().subtract(transfer));

            if (debtor.getAmountPaid().compareTo(BigDecimal.ZERO) == 0) i++;
            if(creditor.getAmountPaid().compareTo(BigDecimal.ZERO) == 0) j++;
        }
        return results;
    }

    private PaymentResultDto getPayment(Participant debtor, Participant creditor, BigDecimal transfer) {
        var payment = new PaymentResultDto(debtor.getUser(), creditor.getUser(), transfer);
        return payment;
    }

}
