import {z} from "zod";

export const schema = z.object({
    firstName: z.string().min(1, {error: "First name must not be blank"}),
    lastName: z.string().min(1, {error: "Last name must not be blank"}),
    email: z.string().min(1, { error: "Email must not be blank" })
    .pipe(
        z.email({ error: "Please enter a valid email address" })
    ),
    phoneNumber: z.string().min(1, {error: "Phone number must not be blank"}),
    address: z.string().min(1, {error: "Address must not be blank"}),
    contractType: z.enum([
        "FULL_TIME",
        "PART_TIME",
        "CONTRACT",
        "TEMPORARY",
        "INTERN"]),
    jobTitle: z.string().min(1, {error: "Job Title must not be blank"}),
    startDate:  z.string()
    .min(1, { error: "Start date must not be blank" })
    .refine(
        (date) => new Date(date) <= new Date(),
        { error: "Start date cannot be in the future" }
    ),
})

export type EmployeeFormData = z.infer<typeof schema>;

