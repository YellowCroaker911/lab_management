/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { SemesterAlterDTO } from "../models/SemesterAlterDTO";
import type { SemesterImportDTO } from "../models/SemesterImportDTO";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class SemesterControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeSemester(
    id: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/semester/removeSemester",
      query: {
        id: id,
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @param requestBody
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static importSemester(
    requestBody: SemesterImportDTO,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/semester/importSemester",
      body: requestBody,
      mediaType: "application/json",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @param requestBody
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterSemester(
    requestBody: SemesterAlterDTO,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/semester/alterSemester",
      body: requestBody,
      mediaType: "application/json",
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
  public static getSemesterById(
    id: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/semester/getSemesterById",
      query: {
        id: id,
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllSemesters(
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/semester/getAllSemesters",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
