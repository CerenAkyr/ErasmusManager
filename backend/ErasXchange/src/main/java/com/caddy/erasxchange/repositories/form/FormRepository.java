package com.caddy.erasxchange.repositories.form;

import com.caddy.erasxchange.models.forms.Form;
import com.caddy.erasxchange.models.forms.PreApprovalForm;
import com.caddy.erasxchange.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface FormRepository<T extends Form> extends JpaRepository<T, Long> {
    T findBySender(User sender);

    List<T> findBySender_BilkentId(Integer bilkentId);
    Optional<T> findBySender_Id(Long id);
}