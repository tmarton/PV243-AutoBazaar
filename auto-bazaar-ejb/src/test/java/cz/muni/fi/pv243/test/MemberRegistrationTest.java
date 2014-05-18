package cz.muni.fi.pv243.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.controller.MemberRegistration;
import cz.muni.fi.pv243.model.Member;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
   @Deployment
   public static Archive<?> createTestArchive() {

       return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage("cz.muni.fi.pv243.model")
            .addPackage("cz.muni.fi.pv243.enums")
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time-hibernate").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time").withTransitivity().asFile())
            .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Inject
   MemberRegistration memberRegistration;

   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
      Member newMember = memberRegistration.getNewMember();
      newMember.setName("Jane Doe");
      newMember.setEmail("jane@mailinator.com");
      newMember.setPhoneNumber("2125551234");
      memberRegistration.register();
      assertNotNull(newMember.getId());
      log.info(newMember.getName() + " was persisted with id " + newMember.getId());
   }
   
}
