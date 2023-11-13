package com.demo.profilematcher.data.entity;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "player_id", nullable = false, unique = true)
  private String playerId;

  @OneToOne
  private ClanEntity clan;

  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
  private List<DeviceEntity> devices;

  @OneToOne (mappedBy = "profile", cascade = CascadeType.ALL)
  private InventoryEntity inventory;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "gender", nullable = false)
  private String gender;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "credential", nullable = false)
  private String credential;

  @Column(name = "level", nullable = false)
  private Integer level;

  @Column(name = "total_play_time", nullable = false)
  private Integer totalPlayTime;

  @Column(name = "total_transactions", nullable = false)
  private Integer totalTransactions;

  @Column(name = "xp", nullable = false)
  private Integer experience;

  @Column(name = "total_refund")
  private Double totalRefund;

  @Column(name = "total_spent")
  private Double totalSpent;

  @Column(name = "created", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = "modified", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime updatedAt;

  @Column(name = "last_purchase", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime lastPurchaseTime;

  @Column(name = "last_session", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime lastSessionTime;

  @Column(name = "birth_date", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
  private OffsetDateTime birthDate;

}
