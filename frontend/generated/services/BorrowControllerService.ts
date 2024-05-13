/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BorrowAdmitDTO } from "../models/BorrowAdmitDTO";
import type { BorrowAlterDTO } from "../models/BorrowAlterDTO";
import type { BorrowCompleteDTO } from "../models/BorrowCompleteDTO";
import type { BorrowImportDTO } from "../models/BorrowImportDTO";
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class BorrowControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeBorrow(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/borrow/removeBorrow",
      query: {
        id: id,
      },
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static importBorrow(
    requestBody: BorrowImportDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/borrow/importBorrow",
      body: requestBody,
      mediaType: "application/json",
    });
  }

  /**
   * @param borrowCompleteDto
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static completeBorrow(
    borrowCompleteDto: BorrowCompleteDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/borrow/completeBorrow",
      query: {
        borrowCompleteDTO: borrowCompleteDto,
      },
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterBorrow(
    requestBody: BorrowAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/borrow/alterBorrow",
      body: requestBody,
      mediaType: "application/json",
    });
  }

  /**
   * @param borrowAdmitDto
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static admitBorrow(
    borrowAdmitDto: BorrowAdmitDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/borrow/admitBorrow",
      query: {
        borrowAdmitDTO: borrowAdmitDto,
      },
    });
  }

  /**
   * @param week
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowsByWeek(
    week: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowsByWeek",
      query: {
        week: week,
      },
    });
  }

  /**
   * @param studentId
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowsByTeacherId(
    studentId: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowsByStudentId",
      query: {
        studentId: studentId,
      },
    });
  }

  /**
   * @param status
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowsByStatus(
    status: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowsByStatus",
      query: {
        status: status,
      },
    });
  }

  /**
   * @param semester
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowsBySemester(
    semester: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowsBySemester",
      query: {
        semester: semester,
      },
    });
  }

  /**
   * @param labId
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowsByLabId(
    labId: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowsByLabId",
      query: {
        labId: labId,
      },
    });
  }

  /**
   * @param id
   * @param token
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getBorrowById(
    id: number,
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getBorrowById",
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
  public static getAllBorrows(
    token: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/borrow/getAllBorrows",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
