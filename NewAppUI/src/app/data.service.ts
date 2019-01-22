/**Data service act as a global variable to store news information and can be used across the components */

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  public newsId : number;
  public title : string;
  public description: string;
  public url :string;
  public imageUrl :string;
  public publishedDate: string;
  public content: string;
}
