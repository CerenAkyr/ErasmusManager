package com.caddy.erasxchange.models.forms;

import com.caddy.erasxchange.models.BaseEntity;
import com.caddy.erasxchange.models.users.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Form extends BaseEntity {


    @Column(name = "time_sent")
    private Instant timeSent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FormApprovalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "rows")
    private List<FormItem> rows = new ArrayList<>();
}