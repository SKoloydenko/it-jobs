package com.sdk.itjobs.controller.vacancy;

import static com.sdk.itjobs.util.constant.UrlConstants.API_V1_USER;

import com.sdk.itjobs.dto.AppMessage;
import com.sdk.itjobs.dto.PageResponse;
import com.sdk.itjobs.dto.vacancy.response.FavouriteVacancyResponse;
import com.sdk.itjobs.dto.vacancy.response.VacancyUserResponse;
import com.sdk.itjobs.exception.ResourceAlreadyExistsException;
import com.sdk.itjobs.exception.ResourceNotFoundException;
import com.sdk.itjobs.service.permission.PermissionService;
import com.sdk.itjobs.service.vacancy.common.VacancyService;
import com.sdk.itjobs.service.vacancy.favourite.FavouriteVacancyService;

import com.sdk.itjobs.util.constant.enumeration.Aggregator;
import com.sdk.itjobs.util.constant.enumeration.ProgrammingLanguage;
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

@RestController
@RequestMapping(API_V1_USER + "/vacancy")
@RequiredArgsConstructor
public class UserVacancyController {
    private final PermissionService permissionService;
    private final FavouriteVacancyService favouriteVacancyService;
    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(value = "programmingLanguage", required = false)
                    ProgrammingLanguage programmingLanguage,
            @RequestParam(value = "minSalary", required = false) Long minSalary,
            @RequestParam(value = "maxSalary", required = false) Long maxSalary,
            @RequestParam(value = "aggregator", required = false) Aggregator aggregator,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageResponse<VacancyUserResponse> vacancies =
                vacancyService.listForUser(
                        permissionService.getPrincipal(),
                        programmingLanguage,
                        minSalary,
                        maxSalary,
                        aggregator,
                        PageRequest.of(page - 1, size));
        return ResponseEntity.ok().body(vacancies);
    }

    @GetMapping("/favourite")
    public ResponseEntity<?> listFavouriteVacancies(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageResponse<FavouriteVacancyResponse> vacancies =
                favouriteVacancyService.list(
                        permissionService.getPrincipal(), PageRequest.of(page - 1, size));
        return ResponseEntity.ok().body(vacancies);
    }

    @PostMapping("/{vacancyId}/favourite")
    public ResponseEntity<?> createFavouriteVacancy(@PathVariable Long vacancyId)
            throws ResourceAlreadyExistsException, ResourceNotFoundException {
        FavouriteVacancyResponse response =
                favouriteVacancyService.createFavouriteVacancy(
                        vacancyId, permissionService.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{vacancyId}/favourite")
    public ResponseEntity<?> deleteFavouriteVacancy(@PathVariable Long vacancyId)
            throws ResourceNotFoundException {
        favouriteVacancyService.deleteFavouriteVacancy(vacancyId, permissionService.getPrincipal());
        return ResponseEntity.ok()
                .body(new AppMessage("Favourite vacancy has been successfully deleted"));
    }
}
