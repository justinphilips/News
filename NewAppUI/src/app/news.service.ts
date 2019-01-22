/** Service call for news service */

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {NEWS} from './news';
import {Result} from './result';
import {UserService} from './user.service';


@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private newsListURL : string;
  private recommendNewsURL :string;
  private wishListURL :string;
  private createNewsURL:string;
  private updateNewsURL:string;
  constructor(
    private http: HttpClient,
    private userService :UserService
  ) {
    this.newsListURL = "http://localhost:8089/news";
    this.recommendNewsURL = "http://localhost:8089/news/addToWishList/";
    this.wishListURL="http://localhost:8089/news/wishList/";
    this.createNewsURL="http://localhost:8089/news";
    this.updateNewsURL="http://localhost:8089/news/";
  }

  getAllNews(): Observable<any>{
    

    return this.http.get<any>(this.newsListURL);
    
  }

  createNews(news):Observable<any>{
    return this.http.post<any>(this.createNewsURL,news);
  }

  updateNews(news,newsId):Observable<any>{
    return this.http.put<any>(this.updateNewsURL+newsId,news);
  }



  recommendNews(favNewsId,userId):Observable<any>{
    const httpOptions={
      headers: new HttpHeaders({
        'userId': userId
      })
    }
    return this.http.post<any>(this.recommendNewsURL+favNewsId,null,httpOptions);
  }
  getAllWishListNews(userId):Observable<any>{
    return this.http.get<any>(this.wishListURL+userId);
  }
}
