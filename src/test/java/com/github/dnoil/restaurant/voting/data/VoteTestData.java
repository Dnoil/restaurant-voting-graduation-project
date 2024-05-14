package com.github.dnoil.restaurant.voting.data;

import com.github.dnoil.restaurant.voting.MatcherFactory;
import com.github.dnoil.restaurant.voting.model.Vote;

import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int VOTE_ID = 1;
    public static final int OLD_VOTE_ID = 3;

    public static final Vote vote1 = new Vote(VOTE_ID, UserTestData.user1, RestaurantTestData.restaurant2);
    public static final Vote vote2 = new Vote(VOTE_ID + 1, UserTestData.user2, RestaurantTestData.restaurant2);
    public static final Vote oldVote = new Vote(OLD_VOTE_ID, UserTestData.user1, RestaurantTestData.restaurant1);

    public static final List<Vote> votes = List.of(vote1, vote2);
    public static final List<Vote> votesOfUser = List.of(vote1, oldVote);

    public static Vote getNew() {
        return new Vote(null, UserTestData.admin, RestaurantTestData.restaurant1);
    }
}
