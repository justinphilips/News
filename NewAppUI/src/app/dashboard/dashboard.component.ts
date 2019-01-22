import { Component, OnInit } from '@angular/core';
import {NewsService} from '../news.service';
import { NEWS } from '../news';
import {UserService} from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[
    NewsService
    ]
})
export class DashboardComponent implements OnInit {

  pageTitle: string = 'News List';
  imageWidth: number = 80;
  imageMargin: number = 2;
  showImage: boolean = true;
  errorMessage: string;
  AlreadyRecommended:NEWS[]=[];
  _listFilter: string;
  get listFilter(): string {
      return this._listFilter;
  }
  set listFilter(value: string) {
      this._listFilter = value;
      this.filteredNews = this.listFilter ? this.performFilter(this.listFilter) : this.news;
  }

  filteredNews: NEWS[];
  news: NEWS[] = [];
  favNewsId:string;
  disableButton=false;
  userId:string;


  constructor(
    private router: Router,
    private newsService: NewsService,
    private userService :UserService,
    private dataService:DataService) {

  }

  onRatingClicked(message: string): void {
      this.pageTitle = 'Movie List: ' + message;
  }

  performFilter(filterBy: string): NEWS[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.news.filter((news: NEWS) =>
            news.title.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

 
// Add To Wishlist / Remove from wishlist
 recommendNews(event,index,selectedNews) : void {
       let text = event.target.outerText;
       this.disableButton=true;
       
       let currVal = (text == "Add To Wishlist") ? "Remove From Wishlist" : "Add To Wishlist";
       
       this.favNewsId = selectedNews.newsId;
              
       
       this.newsService.recommendNews(this.favNewsId,this.userService.userId).subscribe(
               data =>{
                event.target.innerHTML = currVal;
                  }
            );
      }

  toggleImage(): void {
      this.showImage = !this.showImage;
  }
  // changing HTML 
  recommendNewsOK(newsId : any): any {
    var buttonText = "Add To Wishlist";
    for (var i=0;i<this.AlreadyRecommended.length;i++  ){
        if(this.AlreadyRecommended[i].newsId == newsId){
             buttonText = "Remove From Wishlist"
        }
    }
    
   return buttonText;
 }
 redirect() {
     
    this.router.navigate(['./admin']);
  }
  //Update news
  editnewsredirect(event,index){
      
    this.dataService.newsId = this.filteredNews[index].newsId;
    this.dataService.title = this.filteredNews[index].title;
    this.dataService.description = this.filteredNews[index].description;
    this.dataService.url= this.filteredNews[index].url;
    this.dataService.imageUrl = this.filteredNews[index].imageUrl;
    this.dataService.publishedDate = this.filteredNews[index].publishedDate;
    this.dataService.content = this.filteredNews[index].content;

    this.router.navigate(['./admin']);
  }
 ngOnInit(): void {
      this.newsService.getAllNews()
              .subscribe(news => {
                  this.news = news;
                  this.filteredNews = this.news;
                  this.userId=this.userService.userId;
                if(this.userId==undefined){
                    this.router.navigate(['./login']);
                }
              },
                  error => this.errorMessage = <any>error);

              
                  
                  this.newsService.getAllWishListNews(this.userService.userId)
                  .subscribe(news => {
                      this.AlreadyRecommended = news;                    
                  },
                      error => this.errorMessage = <any>error);

  }

}
