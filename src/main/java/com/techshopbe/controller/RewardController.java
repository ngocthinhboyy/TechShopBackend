package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.Reward;
import com.techshopbe.service.RewardService;
import com.techshopbe.service.UserService;

@RestController
@RequestMapping("api/v1/reward")
public class RewardController {
	@Autowired
	RewardService rewardService;
	@Autowired
	UserService userService;

	@GetMapping
	public Object getAll() {
		try {
			List<Reward> listRewards = rewardService.getAll();
			return new ResponseEntity<List<Reward>>(listRewards, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public Object add(@RequestBody final Reward reward) {
		try {
			
			rewardService.addCheckedByBackend(reward);
			userService.updateUserReward(reward.getId());
			return new ResponseEntity<String>("Add Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
	}

	@PutMapping
	public Object update(@RequestBody Reward reward) {
		try {
			rewardService.updateCheckedByBE(reward);
			userService.updateUserReward(reward.getId());
			return new ResponseEntity<String>("Update Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/{id}")
	public Object delete(@PathVariable String id) {
		try {
			int deletedLevel = rewardService.getById(id).getLevel();
			rewardService.delete(id);
			userService.downgradeUserReward(id, deletedLevel);
			return new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Delete Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
