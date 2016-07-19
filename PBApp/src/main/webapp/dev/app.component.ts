import {Component} from '@angular/core';
import {Routes, ROUTER_DIRECTIVES, ROUTER_PROVIDERS, Router} from '@angular/router';



@Component({
    selector: 'app',
    template: ` <header>
            <h1>Pocker@Bander</h1>
        </header>
    
`,
     
    directives: [ROUTER_DIRECTIVES],
    //inputs: ["childValue"]
})
/*
@Routes([
        { path: '/cardinfo/:sysNum/:region', component: CardInfoComponent },
        { path: '/cardinfo', component: CardInfoComponent },
        { path: '/history', component: BreefHistoryComponent},
        { path: '/exthistory', component: ExtHistoryComponent},
        { path: '/error', component: ReadErrorComponent},
        { path: '/payment',component: PaymentСomponent},
        { path: '/successpayment',component: PaymentSuccessComponent},
        { path: '/changepwd', component: ChangePasswordComponent},
        { path: '/regionaladmin', component: MainAdminComponent},
        { path: '/sumode', component: MainSuperUserComponent},
        { path: '/selectregion/:region', component: SelectRegionComponent},
        { path: '/edtregdesc', component: EditRegionDescComponent},
        //{path:'/newcontacts', name: 'NewContact', component: NewContactComponent},
        //{path:'/newcontacts/:workplace', name: 'NewContactFromContact', component: NewContactComponent} вытягивает параметры авотматически
])
*/
export class AppComponent {

    constructor(private router: Router
) {
        router.changes.subscribe((val) => {

        })
    }
    
}

