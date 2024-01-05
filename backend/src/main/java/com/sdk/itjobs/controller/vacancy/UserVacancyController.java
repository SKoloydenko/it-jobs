package com.sdk.itjobs.controller.vacancy;

import com.sdk.itjobs.dto.AppMessage;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.FavouriteVacancyResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.service.permission.PermissionService;
import com.sdk.itjobs.service.vacancy.favourite.FavouriteVacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_USER;

@RestController
@RequestMapping(API_V1_USER + "/vacancy")
@RequiredArgsConstructor
public class UserVacancyController {
    private final PermissionService permissionService;
    private final FavouriteVacancyService favouriteVacancyService;

    @GetMapping("/favourite")
    public ResponseEntity<?> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        PageResponse<FavouriteVacancyResponse> vacancies = favouriteVacancyService.list(permissionService.getPrincipal(), PageRequest.of(page - 1, size));
        return ResponseEntity.ok().body(vacancies);
    }

    @PostMapping("/{vacancyId}/favourite")
    public ResponseEntity<?> createFavouriteVacancy(@PathVariable Long vacancyId) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        FavouriteVacancyResponse response = favouriteVacancyService.createFavouriteVacancy(vacancyId, permissionService.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/favourite/{id}")
    public ResponseEntity<?> deleteFavouriteVacancy(@PathVariable Long id) throws ResourceNotFoundException {
        favouriteVacancyService.deleteFavouriteVacancy(id);
        return ResponseEntity.ok().body(new AppMessage("Favourite vacancy has been successfully deleted"));
    }
}
