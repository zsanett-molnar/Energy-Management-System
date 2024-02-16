import { User } from "../user/user";

export class Device {
    id: number | undefined;
    description : string | undefined;
    address : string | undefined;
    maxConsumption : number | undefined;
    userID : User | undefined;

}
