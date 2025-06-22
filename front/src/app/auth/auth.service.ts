import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from './../../environmet/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  private authUrl = `${environment.apiUrl}/auth`;
  public emailTemp : any ;

  login(data: { email: string; password: string }) {
    return this.http.post<{ accessToken: string }>(`${this.authUrl}/login`, data);
  }

  register(data: any) {
    return this.http.post(`${this.authUrl}/register`, data);
  }

  verify(data: any) {
    return this.http.post(`${this.authUrl}/verify`, data);
  }

  sendOtp(data: any) {
    return this.http.post(`${this.authUrl}/send-otp`, data);
  }

  verifyOtpReset(data: any) {
    return this.http.post(`${this.authUrl}/verify-otp`, data);
  }

  resetPassword(data: any) {
    return this.http.post(`${this.authUrl}/reset-password`, data);
  }

  refresh(token: string) {
    return this.http.get(`${this.authUrl}/getRefreshToken?refreshToken=${token}`);
  }

  getRole(): string | null {
    const token = localStorage.getItem('accessToken');
    if (!token) {
      return null;
    }
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload['role'] || null;
    } catch {
      return null;
    }
  }
}
