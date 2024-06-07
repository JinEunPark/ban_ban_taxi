package com.example.ban_ban_taxi.member_location.repository;

import com.example.ban_ban_taxi.member.model.Member;
import com.example.ban_ban_taxi.member.repositroy.MemberRepository;
import com.example.ban_ban_taxi.member_location.model.MemberLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class MemberLocationRepositoryTest {
    @Autowired
    private MemberLocationRepository memberLocationRepository;

    @Autowired
    private MemberRepository memberRepository;
    private GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 3857);
    private Member member1;
    private Member member2;

    @BeforeEach
    void setUp(){
         member1 = Member.builder()
                .name("member1")
                .password("member1")
                .email("member1@email")
                .build();

         member2 = Member.builder()
                .name("member2")
                .password("member2")
                .email("member2@email")
                .build();
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);
    }

    @Test
    public void save_member_location_test(){
        //given
        Point departLocation = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));

        MemberLocation memberLocation = MemberLocation.builder()
                .departLocation(departLocation)
                .desLocation(desLocation)
                .member(member1)
                .build();
        //when
        MemberLocation saved = memberLocationRepository.save(memberLocation);

        //then
        Assertions.assertNotNull(saved.getGeoId());
    }

    @Test
    public void read_member_location_test(){
        //given
        Point departLocation = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));

        MemberLocation memberLocation = MemberLocation.builder()
                .departLocation(departLocation)
                .desLocation(desLocation)
                .member(member1)
                .build();
        //when
        MemberLocation saved = memberLocationRepository.save(memberLocation);
        Optional<MemberLocation> readed = memberLocationRepository.findById(saved.getGeoId());

        //then
        Assertions.assertTrue(readed.isPresent());
        Assertions.assertNotNull(readed.get());
    }

    @Test
    public void update_member_location_test(){
        //given
        Point departLocation = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));

        Point departLocation2 = geometryFactory.createPoint(new Coordinate(00.712776, -74.005974));
        Point desLocation2 = geometryFactory.createPoint(new Coordinate(00.052235, -118.243683));

        MemberLocation memberLocation = MemberLocation.builder()
                .departLocation(departLocation)
                .desLocation(desLocation)
                .member(member1)
                .build();
        MemberLocation saved = memberLocationRepository.save(memberLocation);
        //when
        saved.setDesLocation(desLocation2);
        saved.setDepartLocation(departLocation2);
        MemberLocation updated = memberLocationRepository.save(memberLocation);

        //then

        Assertions.assertTrue(updated.getDepartLocation().equals(departLocation2));
        Assertions.assertTrue(updated.getDesLocation().equals(desLocation2));
    }


    @Test
    public void delete_member_location_test(){
        //given
        Point departLocation = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));


        MemberLocation memberLocation = MemberLocation.builder()
                .departLocation(departLocation)
                .desLocation(desLocation)
                .member(member1)
                .build();
        MemberLocation saved = memberLocationRepository.save(memberLocation);

        //when
        memberLocationRepository.deleteById(saved.getGeoId());

        //then
        Optional<MemberLocation> memberLocation1 = memberLocationRepository.findById(saved.getGeoId());
        Assertions.assertFalse(memberLocation1.isPresent());
    }

    @Test
    public void find_near_member_test(){
        //given
        Point departLocation = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));

        MemberLocation memberLocation = MemberLocation.builder()
                .departLocation(departLocation)
                .desLocation(desLocation)
                .member(member1)
                .build();

        MemberLocation saved = memberLocationRepository.save(memberLocation);

        Point departLocation2 = geometryFactory.createPoint(new Coordinate(40.712776, -74.005974));
        Point desLocation2 = geometryFactory.createPoint(new Coordinate(34.052235, -118.243683));

        MemberLocation memberLocation2 = MemberLocation.builder()
                .departLocation(departLocation2)
                .desLocation(desLocation2)
                .member(member2)
                .build();
        MemberLocation saved2 = memberLocationRepository.save(memberLocation2);

        //when

        Optional<List<MemberLocation>> opts = memberLocationRepository.findAllNearMemberLocations(
                saved.getDepartLocation(),
                saved.getDesLocation(),
                saved.getMember().getId(),
                1000.0);

        //then
        Assertions.assertTrue(opts.isPresent());
        Assertions.assertNotNull(opts.get().get(0).getGeoId());

    }

}