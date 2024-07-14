package com.dankim.project.app.web.api.user;

import com.dankim.project.app.web.api.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserApiService userApiService;

    @GetMapping
    public ResponseEntity<GetUserResponse> get(@RequestParam Long userId) {
        var userInfo = userApiService.get(userId);
        return ResponseEntity.ok(new GetUserResponse(userInfo));
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> post(@RequestBody CreateUserRequest request) {
        var createdId = userApiService.create(request.nickname());
        return ResponseEntity.ok(new CreateUserResponse(createdId));
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> update(@RequestBody UpdateUserReqeust reqeust) {
        var updated = userApiService.update(reqeust.articleId(), reqeust.nickname(), reqeust.available());
        return ResponseEntity.ok(new UpdateUserResponse(updated));
    }

    @GetMapping("/nickname")
    public ResponseEntity<UserListByNicknameResponse> listByUser(@RequestParam String nickname) {
        var users = userApiService.listByNickname(nickname);
        return ResponseEntity.ok(new UserListByNicknameResponse(users));
    }
}
