package client.core;

import shared.transferobject.dto.ProfileDTO;

public class Storage {
    private static ProfileDTO profile;

    public static ProfileDTO getProfile() {
        return profile;
    }

    public static void setProfile(ProfileDTO profileDTO) {
        profile = profileDTO;
    }
}
