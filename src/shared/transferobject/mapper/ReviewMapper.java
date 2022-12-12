package shared.transferobject.mapper;

import server.database.dao.ProfileDao;
import server.database.entity.Profile;
import server.database.entity.Review;
import shared.transferobject.dto.ReviewDTO;
import shared.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewMapper extends Mapper<Review, ReviewDTO> {
    private static ReviewMapper instance = null;
    private ProfileDao profileDao;

    private ReviewMapper() {
        profileDao = ProfileDao.getInstance();
    }

    public static ReviewMapper getInstance() {
        if (instance == null) {
            instance = new ReviewMapper();
        }
        return instance;
    }

    @Override
    public ReviewDTO entityToDTO(Review entity) {
        Optional<Profile> profileOpt = profileDao.get(entity.getProfileId());
        Profile profile = null;

        if (profileOpt.isPresent())
            profile = profileOpt.get();

        return new ReviewDTO(
                entity.getReview(),
                entity.getRate(),
                Utils.DATE_FORMAT.format(entity.getDate()),
                entity.getCourseName(),
                entity.getProfileId(),
                profile.getFirstName() + " " + profile.getLastName()
        );
    }

    @Override
    public Review DTOToEntity(ReviewDTO dto) {
        Review entity = new Review();
        entity.setReview(dto.getReview());
        entity.setRate(dto.getRate());
        entity.setProfileId(dto.getProfileId());
        entity.setCourseName(dto.getCourseName());
        return entity;
    }
}
