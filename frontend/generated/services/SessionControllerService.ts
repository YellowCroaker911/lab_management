/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { SessionAlterDTO } from "../models/SessionAlterDTO";
import type { SessionImportDTO } from "../models/SessionImportDTO";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class SessionControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeSession(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/session/removeSession",
      query: {
        "id": id
      }
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static importSession(
    requestBody: SessionImportDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/session/importSession",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterSession(
    requestBody: SessionAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/session/alterSession",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getSessionById(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/session/getSessionById",
      query: {
        "id": id
      }
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllSessions(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/session/getAllSessions"
    });
  }
}
