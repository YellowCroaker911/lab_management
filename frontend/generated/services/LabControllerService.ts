/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { LabAlterDTO } from "../models/LabAlterDTO";
import type { LabImportDTO } from "../models/LabImportDTO";
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class LabControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeLab(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/lab/removeLab",
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
  public static importLab(
    requestBody: LabImportDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/lab/importLab",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterLab(
    requestBody: LabAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/lab/alterLab",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param type
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLabsByType(
    type: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabsByType",
      query: {
        "type": type
      }
    });
  }

  /**
   * @param numberPrefix
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLabsByNumberPrefix(
    numberPrefix: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabsByNumberPrefix",
      query: {
        "numberPrefix": numberPrefix
      }
    });
  }

  /**
   * @param namePrefix
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLabsByNamePrefix(
    namePrefix: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabsByNamePrefix",
      query: {
        "namePrefix": namePrefix
      }
    });
  }

  /**
   * @param equipmentNum
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLabsByLeastEquipmentNum(
    equipmentNum: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabsByLeastEquipmentNum",
      query: {
        "equipmentNum": equipmentNum
      }
    });
  }

  /**
   * @param labAdminId
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getLabsByLabAdminId(
    labAdminId: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabsByLabAdminId",
      query: {
        "lab_admin_id": labAdminId
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
  public static getLabById(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getLabById",
      query: {
        "id": id
      }
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllLabs(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/lab/getAllLabs"
    });
  }
}
