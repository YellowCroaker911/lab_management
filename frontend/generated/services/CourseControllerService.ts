/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CourseAdmitDTO } from "../models/CourseAdmitDTO";
import type { CourseAlterDTO } from "../models/CourseAlterDTO";
import type { CourseImportDTO } from "../models/CourseImportDTO";
import type { ResponseDataObject } from "../models/ResponseDataObject";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";

export class CourseControllerService {
  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static removeCourse(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/course/removeCourse",
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
  public static importCourse(
    requestBody: CourseImportDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/course/importCourse",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param requestBody
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static alterCourse(
    requestBody: CourseAlterDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/course/alterCourse",
      body: requestBody,
      mediaType: "application/json"
    });
  }

  /**
   * @param courseAdmitDto
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static admitCourse(
    courseAdmitDto: CourseAdmitDTO
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/course/admitCourse",
      query: {
        "courseAdmitDTO": courseAdmitDto
      }
    });
  }

  /**
   * @param week
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesByWeek(
    week: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByWeek",
      query: {
        "week": week
      }
    });
  }

  /**
   * @param type
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesByType(
    type: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByType",
      query: {
        "type": type
      }
    });
  }

  /**
   * @param teacherId
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesByTeacherId(
    teacherId: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByTeacherId",
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
  public static getCoursesByStatus(
    status: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByStatus",
      query: {
        "status": status
      }
    });
  }

  /**
   * @param semester
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesBySemester(
    semester: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesBySemester",
      query: {
        "semester": semester
      }
    });
  }

  /**
   * @param namePrefix
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesByNamePrefix(
    namePrefix: string
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByNamePrefix",
      query: {
        "namePrefix": namePrefix
      }
    });
  }

  /**
   * @param labId
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCoursesByLabId(
    labId: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCoursesByLabId",
      query: {
        "labId": labId
      }
    });
  }

  /**
   * @param id
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getCourseById(
    id: number
  ): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getCourseById",
      query: {
        "id": id
      }
    });
  }

  /**
   * @returns ResponseDataObject OK
   * @throws ApiError
   */
  public static getAllCourses(): CancelablePromise<ResponseDataObject> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/course/getAllCourses"
    });
  }
}
