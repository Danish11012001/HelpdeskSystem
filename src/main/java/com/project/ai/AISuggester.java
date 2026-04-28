package com.project.ai;

import java.util.*;

public class AISuggester {

    private static final Map<String, String> solutions = new HashMap<>();

    static {

        // 🔐 LOGIN & AUTH
        solutions.put("login,password,signin,credentials", "Reset your password or verify login credentials.");
        solutions.put("account locked,blocked account", "Wait 15 minutes or contact admin to unlock your account.");
        solutions.put("otp,verification code", "Check your email/SMS for OTP or request a new one.");

        // 🌐 NETWORK
        solutions.put("network,internet,connection,offline", "Check your internet connection or restart router.");
        solutions.put("slow internet,lag,latency", "Close background apps or switch to a stable network.");
        solutions.put("wifi not working,disconnect", "Reconnect WiFi or reset network settings.");

        // 💻 SOFTWARE
        solutions.put("app crash,application crash,stopped working", "Restart the application or reinstall it.");
        solutions.put("not opening,won't open", "Run as administrator or reinstall the software.");
        solutions.put("update error,update failed", "Check internet and try updating again.");

        // 🗄 DATABASE
        solutions.put("database,db error,sql error", "Check DB connection, credentials, or server status.");
        solutions.put("data not saving,insert error", "Verify backend logic and database constraints.");
        solutions.put("data missing,record not found", "Check query conditions or database integrity.");

        // ⚙️ SYSTEM
        solutions.put("slow system,performance issue", "Restart system or close heavy applications.");
        solutions.put("high cpu,high memory", "Check task manager and close unnecessary processes.");
        solutions.put("system crash,blue screen", "Update drivers or check hardware issues.");

        // 🖨 HARDWARE
        solutions.put("printer not working,print error", "Check printer connection and drivers.");
        solutions.put("keyboard not working,keys not responding", "Reconnect keyboard or update drivers.");
        solutions.put("mouse not working", "Try different USB port or replace batteries.");

        // 🔌 POWER
        solutions.put("not charging,battery issue", "Check charger and power cable.");
        solutions.put("power issue,not turning on", "Ensure power supply is connected properly.");

        // 📧 EMAIL
        solutions.put("email not sending,email issue", "Check SMTP settings or internet connection.");
        solutions.put("not receiving mail", "Check spam folder or mail filters.");

        // 🔒 SECURITY
        solutions.put("virus,malware", "Run antivirus scan immediately.");
        solutions.put("unauthorized access,hacked", "Change password and enable 2FA.");

        // 🌍 SERVER
        solutions.put("server down,server error", "Check server logs or restart the server.");
        solutions.put("500 error,internal server error", "Check backend logs for exceptions.");
        solutions.put("404 error,page not found", "Verify URL mapping or servlet configuration.");

        // 📁 FILES
        solutions.put("file upload error,upload failed", "Check file size and format.");
        solutions.put("file not downloading", "Check network and browser settings.");

        // 🔊 AUDIO / VIDEO
        solutions.put("no sound,audio issue", "Check volume and audio drivers.");
        solutions.put("camera not working", "Check camera permissions and drivers.");

        // 🧑‍💻 GENERAL
        solutions.put("error,bug,issue", "Restart the application and try again.");
        solutions.put("unknown problem,not working", "Contact admin for further assistance.");

        // EXTRA
        solutions.put("timeout,request timeout", "Check server response time or retry.");
        solutions.put("permission denied,access denied", "Check user roles and permissions.");
        solutions.put("session expired", "Login again to continue.");
        solutions.put("api error", "Check API endpoint and request format.");
        solutions.put("installation failed", "Run installer as admin.");
        solutions.put("compatibility issue", "Check software compatibility.");
        solutions.put("disk full,storage full", "Free up disk space.");
        solutions.put("backup failed", "Check storage and retry backup.");
        solutions.put("sync issue", "Check internet and sync settings.");
        solutions.put("login timeout", "Try logging in again.");
    }

    // ✅ BEST SOLUTION (SMART SCORING)
    public static String getSolution(String issue) {

        if (issue == null || issue.isEmpty()) {
            return "No issue description provided.";
        }

        issue = issue.toLowerCase();

        int maxScore = 0;
        String bestSolution = "No automatic solution available.";

        for (Map.Entry<String, String> entry : solutions.entrySet()) {

            String[] keywords = entry.getKey().split(",");
            int score = 0;

            for (String keyword : keywords) {
                if (issue.contains(keyword.trim())) {
                    score++;
                }
            }

            if (score > maxScore) {
                maxScore = score;
                bestSolution = entry.getValue();
            }
        }

        return bestSolution;
    }

    // 🔥 TOP 3 SOLUTIONS
    public static List<String> getTopSolutions(String issue) {

        issue = issue.toLowerCase();
        Map<String, Integer> scoreMap = new HashMap<>();

        for (Map.Entry<String, String> entry : solutions.entrySet()) {

            String[] keywords = entry.getKey().split(",");
            int score = 0;

            for (String keyword : keywords) {
                if (issue.contains(keyword.trim())) {
                    score++;
                }
            }

            if (score > 0) {
                scoreMap.put(entry.getValue(), score);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(scoreMap.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        List<String> top = new ArrayList<>();

        for (int i = 0; i < Math.min(3, list.size()); i++) {
            top.add(list.get(i).getKey());
        }

        return top;
    }
}