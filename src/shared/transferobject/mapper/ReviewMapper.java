package shared.transferobject.mapper;

import server.database.dao.ProfileDao;
import server.database.entity.Profile;
import server.database.entity.Review;
import shared.transferobject.dto.ReviewDTO;
import shared.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewMapper implements Mapper<Review, ReviewDTO> {
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
        entity.setDate(Utils.stringToDate(dto.getDate()));
        entity.setProfileId(dto.getProfileId());
        return null;
    }

    @Override
    public List<ReviewDTO> allEntitiesToDTOs(List<Review> entities) {
        List<ReviewDTO> dtos = new ArrayList<>();

        for (Review entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    @Override
    public List<Review> allDTOsToEntities(List<ReviewDTO> dtos) {
        List<Review> entities = new ArrayList<>();

        for (ReviewDTO dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
