package trab3;

import trab2.Contact;
import trab2.NoteBook;
import trab2.Utils;

import java.io.*;
import java.util.*;


public class CallReg {
    private String number;
    private Map<String, AnsweredCall> answeredCallMap=new HashMap<>();
    private Map<String, RejectedCall> rejectedCallMap=new HashMap<>();
    private Map<String, SentCall> sentCallMap=new HashMap<>();
    private SortedMap<Time, Call> callMap=new TreeMap<>(Comparator.reverseOrder());
    private NoteBook noteBook=new NoteBook();

    public CallReg(String number){
        this.number=number;
        try{
            load(new File("dataFiles\\"+number+".data"));
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public String getNumber() {
        return number;
    }

    public String getNameFromNum(String number){
        Iterable<Contact> iterable= noteBook.getContactsOf(number);
        if(iterable==null) return null;
        Iterator<Contact> list=iterable.iterator();
        Contact c=list.next();
        if(c==null || list.hasNext()) return null;
        return c.getName();
    }

    public void addAnsweredCall(Time t, String number){
        AnsweredCall toAdd=new AnsweredCall(t, number);
        Utils.actualize(answeredCallMap, ()->number, ()->toAdd, answeredCall -> answeredCall.merge(toAdd));
        Utils.actualize(callMap, ()->t, ()->toAdd, x-> true);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRejectedCall(Time t, String number){
        RejectedCall toAdd=new RejectedCall(t, number);
        Utils.actualize(rejectedCallMap, ()->number, ()->toAdd, rejectedCall -> rejectedCall.merge(toAdd));
        Utils.actualize(callMap, ()->t, ()->toAdd, x-> true);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReceivedCall(Time t, String number, boolean answered){
        if(answered) addAnsweredCall(t, number);
        else addRejectedCall(t, number);
    }

    public void addSentCall(Time t, String number, Duration d){
        SentCall toAdd=new SentCall(t, number, d);
        Utils.actualize(sentCallMap, ()->number, ()->toAdd, sentCall -> sentCall.merge(toAdd));
        Utils.actualize(callMap, ()->t, ()->toAdd, x-> true);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static Comparator<Call> callCmpDescendingTime=new Comparator<Call>() {
        @Override
        public int compare(Call o1, Call o2) {
            return o2.getTime().compareTo(o1.getTime());
        }
    };

    public Iterable<AnsweredCall> getAnsweredCalls(){
        Iterable<AnsweredCall> iterable= answeredCallMap.values();
        LinkedList<AnsweredCall> list=new LinkedList<>();
        for(AnsweredCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<RejectedCall> getRejectedCalls(){
        Iterable<RejectedCall> iterable= rejectedCallMap.values();
        LinkedList<RejectedCall> list=new LinkedList<>();
        for(RejectedCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<SentCall> getSentCalls(){
        Iterable<SentCall> iterable= sentCallMap.values();
        LinkedList<SentCall> list=new LinkedList<>();
        for(SentCall call:iterable)
            list.add(call);
        list.sort(callCmpDescendingTime);
        return list;
    }

    public Iterable<Call> getAllCalls(){
        return callMap.values();
    }

    public void save(File file) throws IOException {
        try(ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(file))) {
            objOut.writeObject(answeredCallMap);
            objOut.writeObject(rejectedCallMap);
            objOut.writeObject(sentCallMap);
            objOut.writeObject(callMap);
            objOut.writeObject(noteBook);
        }
    }

    public void load(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(file))){
            answeredCallMap= (Map<String, AnsweredCall>) objIn.readObject();
            rejectedCallMap= (Map<String, RejectedCall>) objIn.readObject();
            sentCallMap= (Map<String, SentCall>) objIn.readObject();
            callMap= (SortedMap<Time, Call>) objIn.readObject();
            noteBook=(NoteBook) objIn.readObject();
        }
    }

    private String toStringReceivedCallWithName(Call call){
        String name=getNameFromNum(call.getNumber());
        return (name!=null?name:call.getNumber())+" "+call.getTime().toString();
    }

    private String toStringSentCallWithName(SentCall sentCall){
        return toStringReceivedCallWithName(sentCall)+" duration:"+sentCall.getDuration().toString();
    }

    public void autoSave() throws IOException {
        save(new File("dataFiles\\"+number+".data"));
    }

    public String toStringCallWithName(Call call){
        if(call instanceof SentCall)
            return toStringSentCallWithName((SentCall) call);
        return toStringReceivedCallWithName(call);
    }
}
