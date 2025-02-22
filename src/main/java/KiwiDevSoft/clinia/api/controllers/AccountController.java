package KiwiDevSoft.clinia.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import KiwiDevSoft.clinia.api.dto.request.AccountRequest;
import KiwiDevSoft.clinia.api.dto.request.update.AccountUpdateRequest;
import KiwiDevSoft.clinia.api.dto.response.AccountResponse;
import KiwiDevSoft.clinia.infrastructure.abstract_services.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Management", description = "Endpoints for managing user accounts")
public class AccountController {

        private final IAccountService accountService;

        /*----------------------
         * CREATE ACCOUNT
         * ---------------------
         */
        @Operation(summary = "Create a new account", description = "Creates a new user account with email, password, and role.", responses = {
                        @ApiResponse(responseCode = "201", description = "ACCOUNT CREATED"),
                        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
                        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
        @PostMapping
        public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
                return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(request));
        }

        /*----------------------
         * GET ALL ACCOUNTS
         * ---------------------
         */
        @Operation(summary = "Retrieve all accounts with pagination", description = "Fetches a paginated list of accounts. Defaults: page=0, size=10.", parameters = {
                        @Parameter(name = "page", description = "Page number (starts from 0)", schema = @Schema(type = "integer", defaultValue = "0")),
                        @Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer", defaultValue = "10")),

        }, responses = {
                        @ApiResponse(responseCode = "200", description = "SUCCESSFUL"),
                        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
                        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
        @GetMapping
        public ResponseEntity<Page<AccountResponse>> getAll(Pageable pageable) {
                return ResponseEntity.ok(accountService.getAll(pageable));
        }

        /*----------------------
         * GET ACCOUNT BY ID
         * ---------------------
         */
        @Operation(summary = "Retrieve an account by ID", description = "Fetches an account using its unique ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "SUCCESSFUL"),
                        @ApiResponse(responseCode = "404", description = "ACCOUNT NOT FOUND"),
                        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
        @GetMapping("/{id}")
        public ResponseEntity<AccountResponse> getById(@PathVariable String id) {
                return accountService.getById(id)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        /*----------------------
         * UPDATE ACCOUNT
         * ---------------------
         */
        @Operation(summary = "Update an account", description = "Updates the details of an existing account.", responses = {
                        @ApiResponse(responseCode = "200", description = "ACCOUNT UPDATED"),
                        @ApiResponse(responseCode = "404", description = "ACCOUNT NOT FOUND"),
                        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
                        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
        @PutMapping("/{id}")
        public ResponseEntity<AccountResponse> update(
                        @PathVariable String id,
                        @Valid @RequestBody AccountUpdateRequest request) {
                return ResponseEntity.ok(accountService.update(id, request));
        }

        /*----------------------
         * DELETE ACCOUNT (Soft Delete)
         * ---------------------
         */
        @Operation(summary = "Soft delete an account", description = "Marks an account as inactive instead of deleting it permanently.", responses = {
                        @ApiResponse(responseCode = "204", description = "ACCOUNT DEACTIVATED"),
                        @ApiResponse(responseCode = "404", description = "ACCOUNT NOT FOUND"),
                        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable String id) {
                accountService.delete(id);
                return ResponseEntity.noContent().build();
        }
}
