import { AdCategory } from "./adCategory";
import { User } from "./user";

export class Ad {

    id: number;
    adCategory: string;
    owner: User;
    city: string;
    country:string;
    area: number;
    price: number;
    description: string;

}
