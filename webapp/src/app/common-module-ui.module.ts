import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CovalentLayoutModule } from '@covalent/core/layout';
import { CovalentStepsModule } from '@covalent/core/steps';
import { CovalentHttpModule } from '@covalent/http';
import { CovalentDynamicFormsModule } from '@covalent/dynamic-forms';
import { CovalentSearchModule } from '@covalent/core/search';
import { CovalentDialogsModule } from '@covalent/core/dialogs';

import { TdDialogService } from '@covalent/core/dialogs';

import {

} from '@angular/material';
import {
    MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatTableModule,
    MatToolbarModule, MatMenuModule, MatIconModule, MatProgressSpinnerModule, MatListModule,
    MatSelectModule, MatSlideToggleModule, MatDatepickerModule, MatNativeDateModule,MatRadioModule

} from '@angular/material';
@NgModule({
    imports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatProgressSpinnerModule,
        FormsModule,
        MatListModule,
        MatSelectModule,
        MatSlideToggleModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatRadioModule,
        CovalentLayoutModule,
        CovalentStepsModule,
        CovalentHttpModule.forRoot(),
        CovalentDynamicFormsModule,
        CovalentSearchModule,
        CovalentDialogsModule,
        RouterModule
    ],
    exports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatDialogModule,
        MatTableModule,
        MatMenuModule,
        MatIconModule,
        MatProgressSpinnerModule,
        FormsModule,
        MatListModule,
        MatSelectModule,
        MatSlideToggleModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatRadioModule,
        CovalentLayoutModule,
        CovalentStepsModule,
        CovalentDynamicFormsModule,
        RouterModule,
        CovalentDialogsModule,
        CovalentSearchModule,
    ],
    providers:[
        TdDialogService,
    ]
})
export class CommonModuleUi { }