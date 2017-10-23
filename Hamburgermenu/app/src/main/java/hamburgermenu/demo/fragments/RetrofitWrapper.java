package hamburgermenu.demo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Multipart;

/**
 * Created by linus on 2017-10-12.
 */

public class RetrofitWrapper {
    private Retrofit retrofit;
    private RestInterface client;

    public RetrofitWrapper() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/web_guitar_official/webresources/").addConverterFactory(SimpleXmlConverterFactory.create());
        retrofit = builder.build();

        client = retrofit.create(RestInterface.class);
    }

    public void getAppointmentEvents(final RetroCallback<List<Event>> func) {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, List<Event>> task = new AsyncTask<Integer, Integer, List<Event>>() {
            @Override
            protected List<Event> doInBackground(Integer... params) {
                List<TimeReservation> timeReservations = new ArrayList<>();
                List<Appointment> appointments = new ArrayList<>();

                Customers customers = getCustomers();
                int size = customers.size();
                for (int i = 0; i < size; i++) {
                    Customer customer = customers.getCustomer(i);
                    Appointment appointment = getAppointmentById(customer.getAppointmentIdFk());
                    if(appointment == null)
                    {
                        return new ArrayList<Event>();
                    }
                    TimeReservation timeReservation = getTimeReservationsById(appointment.getTimeReservationIdFk());
                    if(timeReservation == null)
                    {
                        return new ArrayList<Event>();
                    }

                    appointments.add(appointment);
                    timeReservations.add(timeReservation);
                }

                return generateEvents(customers, appointments, timeReservations);
            }

            @Override
            protected void onPostExecute(List<Event> arg) {
                if(arg != null)
                {
                    func.onResponse(arg);
                }
            }
        };
        task.execute(0);
    }

    public void getRepairEvents(final RetroCallback<List<Event>> func) {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, List<Event>> task = new AsyncTask<Integer, Integer, List<Event>>() {
            @Override
            protected List<Event> doInBackground(Integer... params) {
                List<TimeReservation> timeReservations = new ArrayList<>();

                Repairs repairs = getRepairs();
                int size = repairs.size();
                for (int i = 0; i < size; i++) {
                    Repair repair = repairs.getRepair(i);
                    TimeReservation timeReservation = getTimeReservationsById(repair.getTimeReservationIdFk());

                    timeReservations.add(timeReservation);
                }

                return generateEvents(repairs, timeReservations);
            }

            @Override
            protected void onPostExecute(List<Event> arg) {
                func.onResponse(arg);
            }
        };
        task.execute(0);
    }

    public void getLeaveEvents(final RetroCallback<List<Event>> func) {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, List<Event>> task = new AsyncTask<Integer, Integer, List<Event>>() {
            @Override
            protected List<Event> doInBackground(Integer... params) {
                List<TimeReservation> timeReservations = new ArrayList<>();

                Leaves leaves = getLeaves();
                int size = leaves.size();
                for (int i = 0; i < size; i++)
                {
                    Leave leave = leaves.getLeave(i);
                    TimeReservation timeReservation = getTimeReservationsById(leave.getTimeReservationIdFk());
                    timeReservations.add(timeReservation);
                }
                return generateEvents(leaves, timeReservations);
            }

            @Override
            protected void onPostExecute(List<Event> arg) {
                func.onResponse(arg);
            }
        };
        task.execute(0);
    }

    private Customers getCustomers() {
        Customers customers = new Customers();
        Call<Customers> call;
        call = client.getAllCustomers();
        try {
            customers = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void postCustomer(Customer customer, final RetroCallback<Customer> func) {
        Call<Customer> call = client.postCustomer(customer);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private TimeReservations getTimeReservations() {
        TimeReservations timeReservations = new TimeReservations();
        Call<TimeReservations> call;
        call = client.getAllTimeResarvations();
        try {
            timeReservations = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeReservations;
    }

    private TimeReservation getTimeReservationsById(int id) {
        TimeReservation timeReservation = new TimeReservation();
        Call<TimeReservation> call = client.getTimeReservationById(id);
        try {
            timeReservation = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeReservation;
    }

    public void postTimeReservation(TimeReservation timeReservation, final RetroCallback<TimeReservation> func) {
        Call<TimeReservation> call = client.postTimeReservation(timeReservation);
        call.enqueue(new Callback<TimeReservation>() {
            @Override
            public void onResponse(Call<TimeReservation> call, Response<TimeReservation> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<TimeReservation> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private Appointments getAppointments() {
        Appointments appointments = new Appointments();
        Call<Appointments> call;
        call = client.getAllAppointments();
        try {
            appointments = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    private Appointment getAppointmentById(int id) {
        Appointment appointment = new Appointment();
        Call<Appointment> call = client.getAppointmentById(id);
        try {
            appointment = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    public void postAppointment(Appointment appointment, final RetroCallback<Appointment> func) {
        Call<Appointment> call = client.postAppointment(appointment);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void postInput(final Customer customer, final TimeReservation timeReservation, final Appointment appointment) {
        postTimeReservation(timeReservation,
                new RetroCallback<TimeReservation>() {
                    @Override
                    public void onResponse(TimeReservation entity) {
                        int fk = entity.getTimeResarvationId();
                        appointment.setTimeReservationIdFk(fk);
                        postAppointment(appointment,
                                new RetroCallback<Appointment>() {
                                    @Override
                                    public void onResponse(Appointment entity) {
                                        int fk = entity.getAppointmentId();
                                        customer.setAppointmentIdFk(fk);
                                        postCustomer(customer,
                                                new RetroCallback<Customer>() {
                                                    @Override
                                                    public void onResponse(Customer entity) {

                                                    }
                                                }
                                        );
                                    }
                                }
                        );
                    }
                }

        );
    }

    private void postInput(final Repair repair, TimeReservation timeReservation) {
        postTimeReservation(timeReservation,
                new RetroCallback<TimeReservation>() {
                    @Override
                    public void onResponse(TimeReservation entity) {
                        int fk = entity.getTimeResarvationId();
                        repair.setTimeReservationIdFk(fk);
                        postRepair(repair,
                                new RetroCallback<Repair>() {
                                    @Override
                                    public void onResponse(Repair entity) {

                                    }
                                }
                        );
                    }
                }

        );
    }

    private void postInput(final Leave leave, TimeReservation timeReservation) {
        postTimeReservation(timeReservation,
                new RetroCallback<TimeReservation>() {
                    @Override
                    public void onResponse(TimeReservation entity) {
                        int fk = entity.getTimeResarvationId();
                        leave.setTimeReservationIdFk(fk);
                        postLeave(leave,
                                new RetroCallback<Leave>() {
                                    @Override
                                    public void onResponse(Leave entity) {

                                    }
                                }
                        );
                    }
                }

        );
    }

    public void postRepairEvent(RepairEvent event) {
        postInput(event.getRepair(), event.getTimeReservation());
    }

    public void postLeaveEvent(LeaveEvent event) {
        postInput(event.getLeave(), event.getTimeReservation());
    }

    public Repairs getRepairs() {
        Repairs repairs = new Repairs();
        Call<Repairs> call;
        call = client.getAllRepairs();
        try {
            repairs = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repairs;
    }

    public void postRepair(Repair repair, final RetroCallback<Repair> func) {
        Call<Repair> call = client.postRepair(repair);
        call.enqueue(new Callback<Repair>() {
            @Override
            public void onResponse(Call<Repair> call, Response<Repair> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Repair> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Leaves getLeaves() {
        Leaves leaves = new Leaves();
        Call<Leaves> call;
        call = client.getAllLeaves();
        try {
            leaves = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    public void postLeave(Leave leave, final RetroCallback<Leave> func) {
        Call<Leave> call = client.postLeave(leave);
        call.enqueue(new Callback<Leave>() {
            @Override
            public void onResponse(Call<Leave> call, Response<Leave> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Leave> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private List<Event> generateEvents(Customers customers, List<Appointment> appointments, List<TimeReservation> timeReservations) {
        List<Event> eventList = new ArrayList<>();
        int size = customers.size();
        for (int i = 0; i < size; i++) {
            Customer customer = customers.getCustomer(i);
            Appointment appointment = appointments.get(i);
            TimeReservation timeReservation = timeReservations.get(i);
            AppointmentEvent event = new AppointmentEvent();
            event.setAppointment(appointment);
            event.setTimeReservation(timeReservation);
            event.setCustomer(customer);
            eventList.add(event);
        }
        return eventList;
    }

    private List<Event> generateEvents(Repairs repairs, List<TimeReservation> timeReservations) {
        List<Event> eventList = new ArrayList<>();
        int size = repairs.size();
        for (int i = 0; i < size; i++) {
            Repair repair = repairs.getRepair(i);
            TimeReservation timeReservation = timeReservations.get(i);
            RepairEvent event = new RepairEvent();
            event.setRepair(repair);
            event.setTimeReservation(timeReservation);
            eventList.add(event);
        }
        return eventList;
    }

    private List<Event> generateEvents(Leaves leaves, List<TimeReservation> timeReservations) {

        List<Event> eventList = new ArrayList<>();
        int size = leaves.size();
        for (int i = 0; i < size; i++) {
            Leave leave = leaves.getLeave(i);
            TimeReservation timeReservation = timeReservations.get(i);
            LeaveEvent event = new LeaveEvent();
            event.setLeave(leave);
            event.setTimeReservation(timeReservation);

            eventList.add(event);
        }
        return eventList;
    }


    //DELETE REPAIR
    private Repair getRepairById(int id) {
        Repair repair = new Repair();
        Call<Repair> call = client.getRepairById(id);
        try {
            repair = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repair;
    }

    private Repair deleteRepair(int repairId) {
        Call<Repair> call = client.deleteRepair(repairId);
        Repair repair = new Repair();
        try {
            repair = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repair;
    }

    private TimeReservation deleteTimeReservation(final int timeReservationId) {
        TimeReservation tr = new TimeReservation();
        Call<TimeReservation> call = client.deleteTimeReservation(timeReservationId);
        try {
            tr = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public void deleteRepairEvent(final RepairEvent repairEvent) {
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                deleteRepair(repairEvent.getRepair().getRepairId());
                deleteTimeReservation(repairEvent.getTimeReservation().getTimeResarvationId());
                return 0;
            }
        };
        task.execute(0);
    }

    //DELETE LEAVE
    private Leave getLeaveById(int id) {
        Call<Leave> call = client.getLeaveById(id);
        Leave leave = new Leave();
        try {
            leave = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leave;
    }

    private Leave deleteLeave(int leaveId) {
        Call<Leave> call = client.deleteLeave(leaveId);
        Leave leave = new Leave();
        try {
            leave = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leave;
    }

    public void deleteLeaveEvent(final LeaveEvent leaveEvent) {
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                deleteLeave(leaveEvent.getLeave().getLeaveId());
                deleteTimeReservation(leaveEvent.getTimeReservation().getTimeResarvationId());
                return 0;
            }
        };
        task.execute(0);
    }

    //DELETE CUSTOMER
    private Customer getCustomerById(int id) {
        Customer customer = new Customer();
        Call<Customer> call = client.getCustomerById(id);
        try {
            customer = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private Customer deleteCustomer(int customerId) {
        Call<Customer> call = client.deleteCustomer(customerId);
        Customer customer = new Customer();
        try {
            customer = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private Appointment deleteAppointment(int appointmentId) {
        Call<Appointment> call = client.deleteAppointment(appointmentId);
        Appointment appointment = new Appointment();
        try {
            appointment = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    public void deleteAppointmentEvent(final AppointmentEvent appointmentEvent) {
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                deleteCustomer(appointmentEvent.getCustomer().getCustomerId());
                deleteAppointment(appointmentEvent.getAppointment().getAppointmentId());
                deleteTimeReservation(appointmentEvent.getAppointment().getTimeReservationIdFk());
                return 0;
            }
        };
        task.execute(0);
    }

    public void postShop(Shop shop, final RetroCallback<Shop> func){
        Call<Shop> call = client.postGuitar(shop);
        call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void postShopTest(final Shop shop){
        Shop s = shop;
        postShop(shop, new RetroCallback<Shop>() {
            @Override
            public void onResponse(Shop entity) {
                //Shop tmp = entity;
                int id = entity.getId();

                //System.out.println(id);

            }
        });
    }

    public void uploadImage(String filePath)
    {
        File file = new File(filePath);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/jpg"),
                        file
                );
        ///storage/emulated/0/DCIM/Camera/IMG_20171018_220321.jpg
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        String descriptionString = file.getName();
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        Call<ResponseBody> call = client.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });

    }
    public void uploadImage(File filePath, final RetroCallback<String> func)
    {
        File file = filePath;
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/jpg"),
                        file
                );
        ///storage/emulated/0/DCIM/Camera/IMG_20171018_220321.jpg
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        String descriptionString = file.getName();
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        Call<ResponseBody> call = client.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
                String s = "";
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                func.onResponse(s);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });

    }

    private Budgets getAllBudgets() {
        Budgets budgets = new Budgets();
        Call<Budgets> call;
        call = client.getAllBudgets();
        try {
            budgets = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return budgets;
    }

    public void getBudgetsEvent(final RetroCallback<Budgets> func) {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, Budgets> task = new AsyncTask<Integer, Integer, Budgets>() {
            @Override
            protected  Budgets doInBackground(Integer... params) {
                Budgets budgets = getAllBudgets();
                return budgets;
            }

            @Override
            protected void onPostExecute(Budgets arg) {
                func.onResponse(arg);
            }

        };
        task.execute(0);
    }
    public void postBudget(Budget budget, final RetroCallback<Budget> func)
    {
        Call<Budget> call = client.postBudget(budget);
        call.enqueue(new Callback<Budget>() {
            @Override
            public void onResponse(Call<Budget> call, Response<Budget> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Budget> call, Throwable t) {

            }
        });

    }
}
