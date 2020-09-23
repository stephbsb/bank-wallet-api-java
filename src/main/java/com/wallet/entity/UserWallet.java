package com.wallet.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_wallet")
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "users",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY) //traz apenas o id
    private User users;

    @JoinColumn(name = "wallet",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

}
