package gr.hua.dit.ds.reference.letter.service.service.impl;

import org.springframework.stereotype.Service;

import gr.hua.dit.ds.reference.letter.service.service.ReferenceLetterService;
import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.repository.ReferenceLetterRequestRepository;

import java.util.List;


@Service
public class ReferenceLetterServiceImpl implements ReferenceLetterService { 
    
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    public ReferenceLetterServiceImpl(ReferenceLetterRequestRepository referenceLetterRequestRepository) {
        super();
        this.referenceLetterRequestRepository = referenceLetterRequestRepository;
    }

    @Override
    public List<ReferenceLetterRequest> getAllRLrequests() {
        return referenceLetterRequestRepository.findAll();
    }

    @Override
    public ReferenceLetterRequest saveRLrequest(ReferenceLetterRequest rlRequest) {
        return referenceLetterRequestRepository.save(rlRequest);
    }

    @Override
    public ReferenceLetterRequest getRLrequestById(int id) {
       return referenceLetterRequestRepository.findById(id).get();
    }

    @Override
    public ReferenceLetterRequest updateRLrequest(ReferenceLetterRequest rlRequest) {
        return referenceLetterRequestRepository.save(rlRequest);
    }

    @Override
    public void deleteRLrequestById(int id) {
        referenceLetterRequestRepository.deleteById(id);
    }

}
