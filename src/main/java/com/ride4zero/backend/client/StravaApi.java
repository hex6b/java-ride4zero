package com.ride4zero.backend.client;

import com.ride4zero.backend.model.StravaActivity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import feign.Headers;

@FeignClient(name = "commentFeignClient", url = "https://www.strava.com/api/v3")
public interface StravaApi {

    @GetMapping("/clubs/752355/activities")
    @Headers("Content-Type: application/json")
    List<StravaActivity> getAllActivities(@RequestHeader("Authorization") String bearerToken);
}
