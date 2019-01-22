/** 
 * Admin Create/Edit news page
 */

import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  news:any={};
  createNews:boolean;
  newsId:any;

  constructor(
    private router: Router,
    private dataService: DataService,
    private newsService : NewsService
    ) { }

  ngOnInit() {
    /** Fetching news Id from dashboard page and checking whether it is create or edit request */
    this.news=this.dataService;
    this.newsId=this.dataService.newsId;
    if(this.newsId==null)
    {
      this.createNews=true;
      this.news= {}
      
    }
    else{
      this.createNews=false;
    }
  }
  redirect(){
    this.dataService.newsId=null;
    this.router.navigate(['dashboard']);
  }
/** Calling create news service */
  CreateNews(){
   if (this.createNews){
    this.newsService.createNews(this.news)
        .subscribe(
            data => {
              this.news =data;
              this.dataService.newsId=null;
               this.router.navigate(['dashboard']);
            },
            error => {
                //this.alertService.error(error.error.message);
                alert("Error While Creating News");
                
            });
   }
   else{
     // calling update news service
    this.newsService.updateNews(this.news,this.newsId)
    .subscribe(
        data => {
          this.news =data;
          this.dataService.newsId=null;
           this.router.navigate(['dashboard']);
        },
        error => {
            //this.alertService.error(error.error.message);
            alert("Error While Updating News");
            
        });
    


   }
    
  }

}
