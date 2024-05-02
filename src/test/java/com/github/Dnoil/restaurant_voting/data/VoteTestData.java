package com.github.Dnoil.restaurant_voting.data;

import com.github.Dnoil.restaurant_voting.MatcherFactory;
import com.github.Dnoil.restaurant_voting.model.Vote;

import java.util.List;

import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.restaurant1;
import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.restaurant2;
import static com.github.Dnoil.restaurant_voting.data.UserTestData.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int VOTE_ID = 1;
    public static final int OLD_VOTE_ID = 3;

    public static final Vote vote1 = new Vote(VOTE_ID, user1, restaurant2);
    public static final Vote vote2 = new Vote(VOTE_ID + 1, user2, restaurant2);
    public static final Vote oldVote = new Vote(OLD_VOTE_ID, user1, restaurant2);

    public static final List<Vote> votes = List.of(vote1, vote2);
    public static final List<Vote> votesWithOld = List.of(vote1, vote2, oldVote);

    public static Vote getNew() {
        return new Vote((Integer) null, admin, restaurant1);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, user1, restaurant1);
    }
}
