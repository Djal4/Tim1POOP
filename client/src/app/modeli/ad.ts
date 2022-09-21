import { AdCategory } from "./adCategory";
import { User } from "./user";

export class Ad {

    id: number;
    adCategoryId?: number;
    owner: User;
    cityId: number;
    city?:string;
    adCategory?:string;
    country:string;
    area: number;
    price: number;
    description: string;

}
