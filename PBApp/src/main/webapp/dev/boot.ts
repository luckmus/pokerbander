// <reference path="../node_modules/ng2-bs3-modal/typings/browser.d.ts"/> 
// <reference path="../node_modules/typescript/lib/lib.core.es6.d.ts"/> 

import { bootstrap }    from '@angular/platform-browser-dynamic';
import {AppComponent} from "./app.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {ROUTER_PROVIDERS} from "@angular/router";

import {HTTP_PROVIDERS} from "@angular/http";
import { provide } from '@angular/core';
import { MODAL_BROWSER_PROVIDERS } from 'angular2-modal/platform-browser/index';

//noinspection TypeScriptValidateTypes
bootstrap(AppComponent, [   ROUTER_PROVIDERS, 
                            HTTP_PROVIDERS, 
                            MODAL_BROWSER_PROVIDERS,
                            provide(LocationStrategy, {useClass: HashLocationStrategy})]);
document.addEventListener('DOMContentLoaded', this);