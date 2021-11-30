import { apiClient } from "./apiClient";
import { ENDPOINTS } from "./enpoint";


//student 
export const getAllStudent = (param) => apiClient.get(ENDPOINTS.STUDENT , param)
export const addStudent = (body) => apiClient.post(ENDPOINTS.STUDENT , body)
export const delStudent = (body) => apiClient.delete(ENDPOINTS.STUDENT , body)