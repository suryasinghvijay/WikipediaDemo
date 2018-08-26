package demo.vijay.surya.com.wikipediademo.network;

import com.google.gson.Gson;

interface NetworkManagerClients<R,C> {
    R getWikiPediaNetworkClient(C client, Gson gson);
}
