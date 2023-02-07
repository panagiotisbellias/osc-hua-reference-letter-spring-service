package gr.hua.dit.ds.reference.letter.service.service;

import java.util.List;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;

public interface ReferenceLetterService {
    List<ReferenceLetterRequest> getAllRLrequests();

    ReferenceLetterRequest saveRLrequest(ReferenceLetterRequest rlRequest);

    ReferenceLetterRequest getRLrequestById(int id);

    ReferenceLetterRequest updateRLrequest(ReferenceLetterRequest rlRequest);

    void deleteRLrequestById(int id);
}
