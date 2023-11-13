package com.demo.profilematcher.data.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.profilematcher.data.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

  Optional<ProfileEntity> findByPlayerId(String playerId);

}
