import { Component, OnInit } from '@angular/core';
import {NEWS} from '../news';
import {NewsService} from '../news.service';
import {UserService} from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
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


  constructor(
    private router: Router,
    private newsService: NewsService,
    private userService :UserService) {

  }

  onRatingClicked(message: string): void {
      this.pageTitle = 'Movie List: ' + message;
  }

  performFilter(filterBy: string): NEWS[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.news.filter((news: NEWS) =>
            news.title.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }


  toggleImage(): void {
      this.showImage = !this.showImage;
  }

// Get all wish list news service
 ngOnInit(): void {
      this.newsService.getAllWishListNews(this.userService.userId)
              .subscribe(news => {
                  this.news = news;
                  this.filteredNews = this.news;
                  console.log(JSON.stringify(this.news));
                  if(this.userService.userId==undefined){
                    this.router.navigate(['./login']);
                  }
                  
              },
                  error => this.errorMessage = <any>error);

                 

  }

}
