import { Injectable } from '@angular/core';

export interface Student {
  regNo: string;
  rollNo: string;
  name: string;
  standard: string;
  school: string;
}

@Injectable({ providedIn: 'root' })
export class StudentService {
  private students: Student[] = [];

  constructor() {
    this.loadFromLocalStorage();
    // If localStorage is empty, initialize with 2 default records
    if (this.students.length === 0) {
      this.students = [
        { regNo: 'R001', rollNo: '1', name: 'Alice', standard: '10', school: 'ABC School' },
        { regNo: 'R002', rollNo: '2', name: 'Bob', standard: '10', school: 'XYZ School' }
      ];
      this.saveToLocalStorage();
    }
  }

  private saveToLocalStorage() {
    localStorage.setItem('students', JSON.stringify(this.students));
  }

  private loadFromLocalStorage() {
    const data = localStorage.getItem('students');
    if (data) this.students = JSON.parse(data);
  }

  getStudents(): Student[] {
    return this.students;
  }

  addStudent(student: Student) {
    this.students.push(student);
    this.saveToLocalStorage(); // persist changes
  }

  updateStudent(student: Student) {
    const index = this.students.findIndex(s => s.regNo === student.regNo);
    if (index !== -1) {
      this.students[index] = student;
      this.saveToLocalStorage(); // persist changes
    }
  }

  deleteStudent(regNo: string) {
    this.students = this.students.filter(s => s.regNo !== regNo);
    this.saveToLocalStorage(); // persist changes
  }

  getStudentByRegNo(regNo: string): Student | undefined {
    return this.students.find(s => s.regNo === regNo);
  }
}