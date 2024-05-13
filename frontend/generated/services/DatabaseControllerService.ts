/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class DatabaseControllerService {
  /**
   * @param path
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static recoveryDatabase(
    path: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/database/recoveryDatabase",
      query: {
        "path": path
      }
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static backup(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/database/backupDatabase"
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllBackUpFiles(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/database/getAllBackUpFiles"
    });
  }

  /**
   * @param path
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static downloadBackUpFile(
    path: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/database/downloadBackUpFile",
      query: {
        "path": path
      }
    });
  }
}
