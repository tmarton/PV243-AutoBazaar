package cz.muni.fi.pv243.controller;

import cz.muni.fi.pv243.dto.MemberDto;
import cz.muni.fi.pv243.services.MemberService;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {

   @Inject
   private Logger log;

   @Inject
   private MemberService memberService;

   @Inject
   private Event<MemberDto> memberEventSrc;

   private MemberDto newMember;

//   @Produces
//   @Named
   public MemberDto getNewMember() {
      return newMember;
   }

   public void register() {
      log.info("Registering " + newMember.getName());
      memberService.save(newMember);
      memberEventSrc.fire(newMember);
      initNewMember();
   }

   @PostConstruct
   public void initNewMember() {
      newMember = new MemberDto();
   }
}
