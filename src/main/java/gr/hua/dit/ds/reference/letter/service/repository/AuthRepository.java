package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.AuthPK;
import gr.hua.dit.ds.reference.letter.service.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authorities, AuthPK>{
}
