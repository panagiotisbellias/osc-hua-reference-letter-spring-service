package project.springboot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.springboot.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="certificates")
public interface CertificateRepository extends JpaRepository<Certificate, Integer>{
}
