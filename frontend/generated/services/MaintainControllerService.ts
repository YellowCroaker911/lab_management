/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { MaintainAlterDTO } from "../models/MaintainAlterDTO";
import type { MaintainCompleteDTO } from "../models/MaintainCompleteDTO";
import type { MaintainImportDTO } from "../models/MaintainImportDTO";
import type { MaintainStartDTO } from "../models/MaintainStartDTO";
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class MaintainControllerService {
  /**
   * @param maintainStartDto
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static startMaintain(
    maintainStartDto: MaintainStartDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/maintain/startMaintain",
      query: {
        "maintainStartDTO": maintainStartDto
      }
    });
  }

  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeMaintain(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/maintain/removeMaintain",
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
  public static importMaintain(
    requestBody: MaintainImportDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/maintain/importMaintain",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param maintainCompleteDto
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static completeMaintain(
    maintainCompleteDto: MaintainCompleteDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/maintain/completeMaintain",
      query: {
        "maintainCompleteDTO": maintainCompleteDto
      }
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterMaintain(
    requestBody: MaintainAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/maintain/alterMaintain",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param teacherId
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getMaintainsByTeacherId(
    teacherId: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getMaintainsByTeacherId",
      query: {
        "teacherId": teacherId
      }
    });
  }

  /**
   * @param status
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getMaintainsByStatus(
    status: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getMaintainsByStatus",
      query: {
        "status": status
      }
    });
  }

  /**
   * @param labId
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getMaintainsByLabId(
    labId: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getMaintainsByLabId",
      query: {
        "labId": labId
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  /**
   * @param labAdminId
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getMaintainsByLabAdminId(
    labAdminId: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getMaintainsByLabAdminId",
      query: {
        "labAdminId": labAdminId
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
  public static getMaintainById(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getMaintainById",
      query: {
        "id": id
      }
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllMaintains(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/maintain/getAllMaintains"
    });
  }
}
