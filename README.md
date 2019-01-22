## News Application ##

This news application uses news api to get the news list.
It uses the news api to list the trending Movies. User can register and login to this application. After login User can add or remove news to his watch list.

This application divided into three parts

```sh
FrontEND - Angular 4
Server - User Authentication and News service
```
* Front end is developed using Angular
* Backend uses springboot, mysql database

api's :

    user:

        post:   localhost:8082/user/register

        {
            "userId":"tamil",
            "firstName":"tamil",
            "lastName":"tamil",
            "password":"123"
        }
        
         output: 

        {
            "message": "Registered Successfully"
        }

        post:   localhost:8082/user/login

        {
            "userId":"tamil",
            "password":"tamil"
        }


        output: 

       HttpStatus.OK

news:

    post:   localhost:8089/news


{
  "newsId": 1,
  "title": "IND economy grew at 3.4% rate in the third quarter - CNBC",
  "description": "The U.S. economy slowed in the third quarter a bit more than previously estimated, but the pace was likely strong enough to keep growth on track to hit the Trump administration's 3 percent target this year.",
  "url": "https://www.cnbc.com/2018/12/20/gdp-q3-2018-final-reading.html",
  "imageUrl": "https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/09/06/105436746-1536257451651gettyimages-1026773056.1910x1000.jpg",
  "publishedDate": "2018-12-21T13:31:53Z",
  "content": "The U.S. economy slowed in the third quarter a bit more than previously estimated, but the pace was likely strong enough to keep growth on track to hit the Trump administration's 3 percent target this year, even as momentum appears to have moderated further e… [+2806 chars]"
}

    put:    localhost:8089/news/1
        output

{
  "newsId": 1,
  "title": "IND economy grew at 3.4% rate in the third quarter - CNBC",
  "description": "The U.S. economy slowed in the third quarter a bit more than previously estimated, but the pace was likely strong enough to keep growth on track to hit the Trump administration's 3 percent target this year.",
  "url": "https://www.cnbc.com/2018/12/20/gdp-q3-2018-final-reading.html",
  "imageUrl": "https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/09/06/105436746-1536257451651gettyimages-1026773056.1910x1000.jpg",
  "publishedDate": "2018-12-21T13:31:53Z",
  "content": "The U.S. economy slowed in the third quarter a bit more than previously estimated, but the pace was likely strong enough to keep growth on track to hit the Trump administration's 3 percent target this year, even as momentum appears to have moderated further e… [+2806 chars]"
}


    getAll:

        get:    localhost:8089/news/

        success


