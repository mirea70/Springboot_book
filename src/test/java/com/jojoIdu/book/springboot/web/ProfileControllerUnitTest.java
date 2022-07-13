package com.jojoIdu.book.springboot.web;

import ch.qos.logback.classic.sift.AppenderFactoryUsingJoran;
import com.jojoIdu.book.springboot.web.dto.ProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;


public class ProfileControllerUnitTest {

    @Test
    public void real_profile_finded() {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile_Ifnot_first_finded() {
        // given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_none_default_finded() {
        // given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}
