/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { ResponseDataUserLoginVO } from "../models/ResponseDataUserLoginVO";
import type { UserAlterDTO } from "../models/UserAlterDTO";
import type { UserAlterPasswordDTO } from "../models/UserAlterPasswordDTO";
import type { UserImportDTO } from "../models/UserImportDTO";
import type { UserLoginDTO } from "../models/UserLoginDTO";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class UserControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeUser(id: number): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/user/removeUser",
      query: {
        id: id,
      },
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataUserLoginVO OK
   * @throws ApiError
   */
  public static login(
    requestBody: UserLoginDTO
  ): CancelablePromise<ResponseDataUserLoginVO> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/user/login",
      body: requestBody,
      mediaType: "application/json",
    });
  }

  /**
   * @param requestBody
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static importUser(
    requestBody: UserImportDTO,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/user/importUser",
      body: requestBody,
      mediaType: "application/json",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterUser(
    requestBody: UserAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/user/alterUser",
      body: requestBody,
      mediaType: "application/json",
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterPassword(
    requestBody: UserAlterPasswordDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/user/alterPassword",
      body: requestBody,
      mediaType: "application/json",
    });
  }

  /**
   * @param role
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getUsersByRole(
    role: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/user/getUsersByRole",
      query: {
        role: role,
      },
    });
  }

  /**
   * @param namePrefix
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getUsersByNamePrefix(
    namePrefix: string,
    token:string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/user/getUsersByNamePrefix",
      query: {
        namePrefix: namePrefix,
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getUserById(id: number): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/user/getUserById",
      query: {
        id: id,
      },
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLoginUser(
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/user/getLoginUser",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllUsers(
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/user/getAllUsers",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
