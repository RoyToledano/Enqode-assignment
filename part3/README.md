# Part 3: Zero-Trust Score Widget

## Overview
The Zero-Trust Score widget represents the Zero-Trust score of a given company.  
The widget includes the company's overall Zero-Trust score along with each metric score and the risk category.  
The component is created using Vue.js and the Vuetify library.

**Examples:**

<br />

<p align="center">
  <img src="https://github.com/user-attachments/assets/00454ea0-11ad-49fb-9b8f-f65021896e00" width="300" style="margin-right: 20px;" />
  <img src="https://github.com/user-attachments/assets/d65fd332-0c2c-41be-a6c4-29c35138b4af" width="300" style="margin-right: 20px;" />
  <img src="https://github.com/user-attachments/assets/fc2cc149-107f-4e5c-be1d-45295df58f19" width="300" />
</p>

<br />

## Deployment
### Option 1:
* You can test the component using the Vuetify Playground by clicking [here](https://play.vuetifyjs.com/#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).
* Since the Vuetify Playground doesn't allow loading files from external URLs directly, the JSON data is part of the component's data object. You can modify (add more companies, change company data...) here:
<img src="https://github.com/user-attachments/assets/14d02b07-0f8c-464e-a616-cdbff4ddb68d" width="600" style="margin-right: 20px;" />

<br />

For your convenience, you can use [this](https://github.com/user-attachments/files/17450987/zero-trust-data.json) JSON file with two more companies' data.

### Option 2:
* You can integrate the widget within your Vue application.
* Download the component from here.
* Add the component file to the "components" directory in your Vue application project.
* Create a new directory inside the "public" directory named "data".
* Add the JSON file provided in Option 1 above, or create another JSON file named "zero-trust-data" and make sure your new JSON file follows the same structure as the provided JSON file in Option 1.
* Where you want to use the widget in your application, import the component in the script code:
  ```
  import ZeroTrustScoreWidget from './components/ZeroTrustScoreWidget.vue';
  ```
  Then, place the Zero-Trust Score component where you wish to use it in the relevant component:
  ```
  <zero-trust-score-widget />
  ```


